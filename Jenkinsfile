pipeline{
        agent {
          docker{
                    image "maven:3.6.0-jdk-8"
                    label "docker"
                }
        }
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