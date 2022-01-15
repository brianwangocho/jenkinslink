pipeline{
        agent any
        triggers{
        pollSCM '* *  *  * *'
        }

        stages{
            stage("build"){
                steps{
                    sh "mv -version"
                    sh  "mv clean install`"
                }
            }

        }



}