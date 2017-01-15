$name$

### Deploying

#### Prepare environment

> The following is the summary of instructions from the [Google Cloud Functions documentation page](https://cloud.google.com/functions/docs/quickstart).
 
    // install Google Cloud SDK (Archlinux)
    \$ pacaur -S google-cloud-sdk
    
    // install the gcloud tool's beta commands component
    \$ gcloud components install beta
    
    // create a new Cloud Storage bucket for your function
    \$ gsutil mb -p <project-id> gs://$name;format="lower,hyphen"$

#### Deploing the function

To deploy with a HTTP trigger, run:

    \$ sbt "gcDeploy <project-id>"
    
Or to deploy with a Pub/Sub topic trigger, run:

    \$ sbt "gcDeploy <project-id> <pub-sub-topic>"
