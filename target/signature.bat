keytool -genkey -alias signature -keystore monStore
"C:\Program Files\Java\jdk1.7.0_60\bin\jarsigner.exe" -keystore monStore -signedjar sMLC.jar MLC.jar signature