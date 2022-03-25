package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun mvvmCallback(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) mvvmCallbackKt(provider) else mvvmCallbackJava(provider)

fun mvvmCallbackKt(provider: ArmsPluginTemplateProviderImpl) = """
    package ${provider.callbackPackageName.value}

    ${commonAnnotation()}
    interface ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}Callback {

    }
""".trimIndent()

fun mvvmCallbackJava(provider: ArmsPluginTemplateProviderImpl) = """
    package ${provider.callbackPackageName.value};

    ${commonAnnotation()}
    public interface ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}Callback {

    }
""".trimIndent()