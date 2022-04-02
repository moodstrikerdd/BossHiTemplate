package other.res.layout

import other.MVVMPluginTemplateProviderImpl

fun simpleLayout(provider: MVVMPluginTemplateProviderImpl) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="viewModel"
            type="${provider.viewModelPackageName.value}.${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}ViewModel" />

        <variable
            name="callback"
            type="${provider.callbackPackageName.value}.${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}Callback" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/app_white">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""