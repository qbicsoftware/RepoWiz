package life.qbic.repowiz.model

class SubmissionModel {
    RepoWizProject project
    List<RepoWizSample> samples
    List<String> missingValues //todo required to communicate missing fields ??
    String uploadType

    SubmissionModel(RepoWizProject project, List<RepoWizSample> samples){
        this.project = project
        this.samples = samples
    }

    HashMap getAllProperties() {
        HashMap properties = new HashMap()
        //do so to not overwrite project properties!!
        properties << project.properties

        samples.each {sample ->
            properties << sample.properties
        }
        return properties
    }

    /*HashMap<String,HashMap> getAllCharacteristics(){
        HashMap characteristics = new ArrayList()

        samples.each {sample ->
            HashMap sampleCharactics = sample.characteristics
            String name = sample.sampleName
            characteristics.put(name,sampleCharactics)
        }

        return characteristics
    }

    HashMap<String,List> getAllRawFiles(){
        HashMap rawFiles = new ArrayList()

        samples.each {sample ->
            List files = sample.rawFiles
            String name = sample.sampleName
            rawFiles.put(name, files)
        }

        return rawFiles
    }*/

    HashMap<String,String> projectProperties(){
        return project.properties
    }

    HashMap<String,HashMap<String,String>> sampleProperties(){
        HashMap<String, HashMap<String,String>> sampleProperties = new HashMap<>()

        samples.each {sample ->
            sampleProperties.put(sample.sampleName, sample.properties)
        }

        return sampleProperties
    }

}
