package other

import com.android.tools.idea.wizard.template.*
import java.io.File


/**
 * Created on 2021/4/19 16:53
 * @author Love_xie
 * module name is ArmsPluginTemplateProviderImpl
 */
class MVVMPluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(armsTemplate)

    companion object {
        private const val MIN_API = 23
    }

    private val armsTemplate: Template
        get() = template {
            //revision = 2
            name = "Boss Hi MVVM一键生成"
            description = "一键创建Boss Hi MVVM全部组件"
            minApi = MIN_API
            //minBuildApi = MIN_API
            category = Category.Activity
            formFactor = FormFactor.Mobile
            screens = listOf(
                WizardUiContext.ActivityGallery,
                WizardUiContext.MenuEntry,
                WizardUiContext.NewProject,
                WizardUiContext.NewModule
            )
            thumb { File("template_blank_activity.png") }

            widgets(
                TextFieldWidget(pageName),
                PackageNameWidget(appPackageName),

                CheckBoxWidget(needActivity),
                TextFieldWidget(activityLayoutName),
                CheckBoxWidget(generateActivityLayout),

                CheckBoxWidget(needFragment),
                TextFieldWidget(fragmentLayoutName),
                CheckBoxWidget(generateFragmentLayout),

                CheckBoxWidget(needViewModel),
                TextFieldWidget(viewModelPackageName),

                CheckBoxWidget(needCallback),
                TextFieldWidget(callbackPackageName),

                LanguageWidget()
            )

            //创建所需文件
            recipe = { te ->
                //val (projectData, srcOut, resOut) = te as ModuleTemplateData
                armsRecipe(this@MVVMPluginTemplateProviderImpl, (te as ModuleTemplateData))
            }
        }


    var applicationName = ""
    var resourcePrefix = ""

    /** 新建页面名称 */
    val pageName = stringParameter {
        name = "Page Name"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.STRING)
        default = "Main"
        help = "请填写页面名,如填写 Main,会自动生成 MainActivity, MainPresenter 等文件"
    }

    /** 包名 */
    val appPackageName = stringParameter {
        name = "Root Package Name"
        constraints = listOf(Constraint.PACKAGE)
        default = "com.mycompany.myapp"
        help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:me.jessyan.arms"
    }

    /** 是否需要 Activity */
    val needActivity = booleanParameter {
        name = "Generate Activity"
        default = true
        help = "是否需要生成 Activity ? 不勾选则不生成"
    }

    /** layout xml 文件 */
    val activityLayoutName = stringParameter {
        name = "Activity Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { "${resourcePrefix}${activityToLayout(pageName.value)}" }
        default = "activity_main"
        visible = { needActivity.value }
        help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    /** 是否需要 layout xml 文件 */
    val generateActivityLayout = booleanParameter {
        name = "Generate Activity Layout"
        default = true
        visible = { needActivity.value }
        help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
    }


    /** 是否需要 Fragment */
    val needFragment = booleanParameter {
        name = "Generate Fragment"
        default = false
        visible = { !needActivity.value }
        help = "是否需要生成 Fragment ? 不勾选则不生成"
    }

    /** Fragment xml 文件 */
    val fragmentLayoutName = stringParameter {
        name = "Fragment Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = {  "${resourcePrefix}${fragmentToLayout(pageName.value)}"  }
        default = "fragment_main"
        visible = { needFragment.value }
        help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    /** 是否需要生成 Fragment layout 文件 */
    val generateFragmentLayout = booleanParameter {
        name = "Generate Fragment Layout"
        default = true
        visible = { needFragment.value }
        help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
    }

    /**  mvvm 相关 */
    val needViewModel = booleanParameter {
        name = "Generate ViewModel"
        default = true
        help = "是否需要生成 ViewModel ? 不勾选则不生成"
    }
    val viewModelPackageName = stringParameter {
        name = "ViewModel Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "${appPackageName.value}.viewmodel" }
        default = "${appPackageName.value}.viewmodel"
        visible = { needViewModel.value }
        help = "ViewModel 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }
    val needCallback = booleanParameter {
        name = "Generate Callback"
        default = true
        help = "是否需要生成 Callback ? 不勾选则不生成"
    }
    val callbackPackageName = stringParameter {
        name = "callback Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "${appPackageName.value}.callback" }
        default = "${appPackageName.value}.callback"
        visible = { needCallback.value }
        help = "Callback 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

}