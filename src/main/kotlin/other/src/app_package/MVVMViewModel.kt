package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun mvvmViewModel(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) mvvmViewModelKt(provider) else mvvmViewModelJava(provider)


fun mvvmViewModelKt(provider: ArmsPluginTemplateProviderImpl) = """
    package ${provider.viewModelPackageName.value}

    import android.app.Application

    import com.twl.hi.foundation.base.FoundationViewModel

    ${commonAnnotation()}
    class ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}ViewModel(application: Application) : FoundationViewModel(application) {

    }
""".trimIndent()

fun mvvmViewModelJava(provider: ArmsPluginTemplateProviderImpl) = """
    package ${provider.viewModelPackageName.value};

    import android.app.Application;

    import com.twl.hi.foundation.base.FoundationViewModel;

    ${commonAnnotation()}
    public class ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}ViewModel extends FoundationViewModel {

        public ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}ViewModel(Application application) {
            super(application);
        }
    }
""".trimIndent()
