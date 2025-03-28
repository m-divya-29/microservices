Set jdk to 21 to just this project(powershell):
- $env:JAVA_HOME="C:\Users\dmahanka\Downloads\jdk-21.0.5_windows-x64_bin\jdk-21.0.5"
- $env:PATH="$env:JAVA_HOME\bin;$env:PATH"
If you face Lombok annotations not working as expected:
- close the project, delete the .idea and target folders and reopen the project.