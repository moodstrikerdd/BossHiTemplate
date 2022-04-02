package other

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.res.layout.simpleLayout
import other.src.app_package.*
import other.src.armsManifest
import java.io.File

fun RecipeExecutor.armsRecipe(provider: MVVMPluginTemplateProviderImpl, data: ModuleTemplateData) {
    provider.applicationName = data.projectTemplateData.applicationPackage ?: data.packageName

    if (provider.needActivity.value) {
        mergeXml(armsManifest(provider, data), File(data.manifestDir, "AndroidManifest.xml"))
    }

    if (provider.needActivity.value && provider.generateActivityLayout.value) {
        save(simpleLayout(provider), File(data.resDir, "layout/${provider.activityLayoutName.value}.xml"))
    } else if (provider.needFragment.value && provider.generateFragmentLayout.value) {
        save(simpleLayout(provider), File(data.resDir, "layout/${provider.fragmentLayoutName.value}.xml"))
    }

    val languageSuffix = if (data.projectTemplateData.language == Language.Java) "java" else "kt"
    val isKt = data.projectTemplateData.language == Language.Kotlin
    if (provider.needActivity.value) {
        val activityFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.appPackageName.value)}/${provider.pageName.value}Activity.$languageSuffix"
        )
        save(mvvmActivity(isKt, provider), activityFile)
        open(activityFile)
    } else if (provider.needFragment.value) {
        val fragmentFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.appPackageName.value)}/${provider.pageName.value}Fragment.$languageSuffix"
        )
        save(mvvmFragment(isKt, provider), fragmentFile)
        open(fragmentFile)
    }

    if (provider.needViewModel.value) {
        val contractFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.viewModelPackageName.value)}/${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}ViewModel.$languageSuffix"
        )
        save(mvvmViewModel(isKt, provider), contractFile)
    }
    if (provider.needCallback.value) {
        val presenterFile = File(
            data.rootDir,
            "${fFmSlashedPackageName(provider.callbackPackageName.value)}/${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}Callback.$languageSuffix"
        )
        save(mvvmCallback(isKt, provider), presenterFile)
    }
}

fun fFmSlashedPackageName(oVar: String): String {
    return "src/main/java/${oVar.replace('.', '/')}"
}