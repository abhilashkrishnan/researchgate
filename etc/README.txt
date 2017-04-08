This project is created using community edition of IntelliJ IDEA.
Gradle build system is used to build the application.

HOW TO BUILD THE APPLICATION
***********************************************
Run 'gradle build' from the base directory of the project. A directory named 'build/distributions'
will be created in the base directory of the project. You will be able to find 'stream-sampler-0.1.0.zip'
or 'stream-sampler-0.1.0.tar' file available in the directory.

HOW TO RUN THE APPLICATION
**************************************************
Unzip 'stream-sampler-0.1.0.zip' or 'stream-sampler-0.1.0.tar' file to any directory of your choice.
At the command prompt execute the following commands from the unzipped directory

 1) cat input.txt | ./stream-sampler 5
 2) dd if=/dev/urandom count=100 bs=1MB | base64 | ./stream-sampler 5

HOW TO CLEAN THE BUILD
****************************************************
Run  'gradle clean'


