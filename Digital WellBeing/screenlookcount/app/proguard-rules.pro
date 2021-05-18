# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-printmapping build/outputs/mapping/release/mapping.txt

-dontwarn com.gc.materialdesign.views.**

-keep public class com.sohumngs.uapps.dw.db.** {
  *;
}

-keep public class com.sohumngs.uapps.dw.fragment.** {
  *;
}

-keep class com.sohumngs.uapps.dw.utils.Utils**
-keepclassmembers class com.sohumngs.uapps.dw.utils.Utils** {
    *;
}

-keep class com.sohumngs.uapps.ActivityMain**
-keepclassmembers class com.sohumngs.uapps.ActivityMain** {
    *;
}

-keep class com.sohumngs.uapps.dw.calendar.CalendarAdapter**
-keepclassmembers class com.sohumngs.uapps.dw.calendar.CalendarAdapter** {
    *;
}