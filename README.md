# Autologin to StackOverflow

So, you want to get a [Fanatic][fanatic-badge] badge on StackOverflow? But it's not very easy not to forget to login on it every day 100 times. Especially when you are on your vacation.. I had a 86-in-line :( and it was my third try.

Just schedule this [Selenium][selenium] test with cron and get your badge!

## How to schedule

It is easy! You'll only need [Xvfb][xvfb] installed for headless run.

Put a script like this somewhere on you FS, in my case it is ~/scipts/autostack.sh:

    #!/bin/bash

    export JAVA_HOME=/tools/java/jdk1.7.0_17
    export M2_HOME=/tools/maven/apache-maven-3.0.5
    export PATH=${PATH}:${JAVA_HOME}/bin:${M2_HOME}/bin

    date=`date +%Y-%m-%d-%H-%M-%S`

    Xvfb :10 -ac&
    export DISPLAY=:10

    pushd ~/projects/stack-autologin
    mvn clean install
    popd

    killall Xvfb

And now schedule it with cron:

    crontab -e

    ...

    */15 * * * * /home/madhead/scripts/autostack.sh

Testsuite tends to fail сonstantly, so I scheduled it to run every 15 minutes, which gives me 96 runs per day with a good chance to succeed.

[fanatic-badge]: http://stackoverflow.com/help/badges/83/fanatic
[selenium]: http://seleniumhq.org
[xvfb]: http://www.x.org/archive/current/doc/man/man1/Xvfb.1.xhtml
