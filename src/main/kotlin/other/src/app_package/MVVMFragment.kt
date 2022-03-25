package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation
import other.layoutToDataBinding

fun mvvmFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) mvvmFragmentKt(provider) else mvvmFragmentJava(provider)

private fun mvvmFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.appPackageName.value}

import android.os.Bundle

import ${provider.callbackPackageName.value}.${provider.pageName.value}FragmentCallback
import ${provider.viewModelPackageName.value}.${provider.pageName.value}FragmentViewModel
import ${provider.appPackageName.value}.databinding.${layoutToDataBinding(provider.fragmentLayoutName.value)}Binding
import com.twl.hi.foundation.base.fragment.FoundationVMFragment

${commonAnnotation()}
class ${provider.pageName.value}Fragment private constructor() : FoundationVMFragment<${layoutToDataBinding(provider.fragmentLayoutName.value)}Binding, ${provider.pageName.value}FragmentViewModel>(), ${provider.pageName.value}FragmentCallback {

    override fun getContentLayoutId() = R.layout.${provider.fragmentLayoutName.value}

    override fun getCallbackVariable() = BR.callback

    override fun getCallback() = this

    override fun getBindingVariable() = BR.viewModel

    companion object {
        @JvmStatic
        fun newInstance() = ${provider.pageName.value}Fragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
"""

private fun mvvmFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.appPackageName.value};

import android.os.Bundle;

import ${provider.callbackPackageName.value}.${provider.pageName.value}FragmentCallback;
import ${provider.viewModelPackageName.value}.${provider.pageName.value}FragmentViewModel;
import ${provider.appPackageName.value}.databinding.${layoutToDataBinding(provider.fragmentLayoutName.value)}Binding;
import com.twl.hi.foundation.base.fragment.FoundationVMFragment;

${commonAnnotation()}
public class ${provider.pageName.value}Fragment extends FoundationVMFragment<${layoutToDataBinding(provider.fragmentLayoutName.value)}Binding, ${provider.pageName.value}FragmentViewModel> implements ${provider.pageName.value}FragmentCallback {
    @Override
    public int getContentLayoutId() {
        return R.layout.${provider.fragmentLayoutName.value};
    }

    @Override
    public int getCallbackVariable() {
        return BR.callback;
    }

    @Override
    public Object getCallback() {
        return this;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public static ${provider.pageName.value}Fragment newInstance() {
        Bundle bundle = new Bundle();
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
    
"""