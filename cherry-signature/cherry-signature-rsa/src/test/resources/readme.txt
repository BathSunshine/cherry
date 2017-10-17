1.创建jks
keytool -genkey -alias test -keyalg RSA -keysize 1024 -keystore test.jks -validity 365
http://jingyan.baidu.com/article/b0b63dbfe18eff4a483070f4.html

2.导入jks
keytool -import -alias "my server cert" -file server.cer -keystore my.jks