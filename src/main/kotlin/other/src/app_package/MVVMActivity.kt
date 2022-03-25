package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation
import other.layoutToDataBinding

fun mvvmActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) mvvmActivityKt(provider) else mvvmActivityJava(provider)

private fun mvvmActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.appPackageName.value}

import android.content.Context
import android.content.Intent

import ${provider.callbackPackageName.value}.${provider.pageName.value}ActivityCallback
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ActivityViewModel
import ${provider.appPackageName.value}.databinding.${layoutToDataBinding(provider.activityLayoutName.value)}Binding
import com.twl.hi.foundation.base.activity.FoundationVMActivity

import lib.twl.common.util.AppUtil

${commonAnnotation()}
class ${provider.pageName.value}Activity : FoundationVMActivity<${layoutToDataBinding(provider.activityLayoutName.value)}Binding, ${provider.pageName.value}ActivityViewModel>(), ${provider.pageName.value}ActivityCallback {
    override fun getContentLayoutId() = R.layout.${provider.activityLayoutName.value}

    override fun getCallbackVariable() = BR.callback

    override fun getCallback() = this

    override fun getBindingVariable() = BR.viewModel

    companion object {
        @JvmStatic
        fun intentStart(context: Context) {
            val intent = Intent(context, ${provider.pageName.value}Activity::class.java)
            AppUtil.startActivity(context, intent)
        }
    }
}

"""

private fun mvvmActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.appPackageName.value};

import android.content.Context;
import android.content.Intent;

import ${provider.callbackPackageName.value}.${provider.pageName.value}ActivityCallback;
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ActivityViewModel;
import ${provider.appPackageName.value}.databinding.${layoutToDataBinding(provider.activityLayoutName.value)}Binding;
import com.twl.hi.foundation.base.activity.FoundationVMActivity;

import lib.twl.common.util.AppUtil;

${commonAnnotation()}
public class ${provider.pageName.value}Activity extends FoundationVMActivity<${layoutToDataBinding(provider.activityLayoutName.value)}Binding, ${provider.pageName.value}ActivityViewModel> implements ${provider.pageName.value}ActivityCallback {
    @Override
    public int getContentLayoutId() {
        return R.layout.${provider.activityLayoutName.value};
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

    public static void intentStart(Context context) {
        Intent intent = new Intent(context, ${provider.pageName.value}Activity.class);
        AppUtil.startActivity(context, intent);
    }
}
    
"""