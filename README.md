# Ecommerce using microservices
## Commonly faced issues
### Set jdk to 21 to just this project(powershell)
```
$env:JAVA_HOME="C:\Users\dmahanka\Downloads\jdk-21.0.5_windows-x64_bin\jdk-21.0.5"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
```
### If Lombok annotations don't work as expected
- remove it from build/plugins
- close the project, delete the `.idea` and `target` folders and reopen the project.