package other.src

import com.android.tools.idea.wizard.template.ModuleTemplateData
import other.ArmsPluginTemplateProviderImpl

fun armsManifest(provider: ArmsPluginTemplateProviderImpl, data: ModuleTemplateData) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application>
    ${
        if (data.isNewModule) {
        """
        <activity 
            android:name="${provider.appPackageName.value}.${provider.pageName.value}Activity"
            android:screenOrientation="portrait">
	    </activity> 
        """
        } else {
        """
        <activity
	        android:name="${provider.appPackageName.value}.${provider.pageName.value}Activity"
            android:screenOrientation="portrait"
	        />
        """
        }
    }
    </application>
</manifest>
"""