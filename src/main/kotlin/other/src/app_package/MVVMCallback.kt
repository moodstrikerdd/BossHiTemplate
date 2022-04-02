package other.src.app_package

import other.MVVMPluginTemplateProviderImpl
import other.commonAnnotation

fun mvvmCallback(isKt: Boolean, provider: MVVMPluginTemplateProviderImpl) =
    if (isKt) mvvmCallbackKt(provider) else mvvmCallbackJava(provider)

fun mvvmCallbackKt(provider: MVVMPluginTemplateProviderImpl) = """
    package ${provider.callbackPackageName.value}

    ${commonAnnotation()}
    interface ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}Callback {

    }
""".trimIndent()

fun mvvmCallbackJava(provider: MVVMPluginTemplateProviderImpl) = """
    package ${provider.callbackPackageName.value};

    ${commonAnnotation()}
    public interface ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}Callback {

    }
""".trimIndent()