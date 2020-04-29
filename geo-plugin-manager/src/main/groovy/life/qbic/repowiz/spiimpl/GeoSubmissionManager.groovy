package life.qbic.repowiz.spiimpl

import life.qbic.repowiz.finalise.api.SubmissionManager
import life.qbic.repowiz.mapping.GeoMapper
import life.qbic.repowiz.model.RepoWizProject
import life.qbic.repowiz.model.RepoWizSample
import life.qbic.repowiz.model.SubmissionModel
import life.qbic.repowiz.submissionTypes.GeoHtsSubmission
import life.qbic.repowiz.submissionTypes.GeoSubmission
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.jcodings.util.Hash

class GeoSubmissionManager implements SubmissionManager{

    private SubmissionModel geoSubmissionModel
    private GeoMapper mapper
    private GeoSubmission geoSubmission

    private static final Logger LOG = LogManager.getLogger(GeoSubmissionManager.class)

    GeoSubmissionManager(){
        LOG.info "Creating Geo Submission Manager"
        mapper = new GeoMapper()
    }

    @Override
    List validateSubmissionModel(SubmissionModel model) {//idea load the upload/submission type from the model
        LOG.info "Mapping metadata from RepoWiz to Geo"
        mapMetadata(model)

        //check if all required fields are filled
        //todo with hashmap: overwrite fields that are there multiple times --> samples
        HashMap props = model.getAllProperties()
        LOG.info "Determine missing fields from submission"
        List missingFields = geoSubmission.determineMissingFields(props)

        //mark required field values in output sheet
        LOG.info "Mark missing fields in template"
        geoSubmission.markMissingFieldsInTemplate()

        //answer with validation status and missing fields
        if (missingFields == []) LOG.info "The submission is valid, all required fields are defined"

        return missingFields
    }

    @Override
    SubmissionModel getProviderSubmissionModel() {
        return geoSubmissionModel
    }

    @Override
    void downloadSubmission(String fileName) {
        List<HashMap<String,String>> samples = []
        println geoSubmissionModel.sampleProperties()
        //add sample name to properties and convert hashmap to list
        //todo other place to do that?
        geoSubmissionModel.sampleProperties().each {sampleName, properties ->
            HashMap sampleProperties = new HashMap()
            sampleProperties << properties
            sampleProperties << ["samples_Sample name":sampleName]

            samples << sampleProperties
        }

        geoSubmission.writeToWorkbook(geoSubmissionModel.projectProperties(), samples)
        geoSubmission.downloadFile(fileName)
    }

    void createGeoSubmissionObject(String uploadType){//called in the SubmissionManager

        switch (uploadType){
            case "hts": //defined in the repository description (json file)
                geoSubmission = new GeoHtsSubmission()
                break
            case "affymetrix_GE":
                //whole gene expression
                //todo add Class extending the GeoSubmission-Object with information about this type
                break
        }
    }

    void mapMetadata(SubmissionModel model){
        //map project data
        HashMap mappedProjectProps = mapProperties(model.project.properties)
        RepoWizProject mappedProject = new RepoWizProject(model.project.projectID, mappedProjectProps)
        //map sample data
        List<RepoWizSample> mappedSamples = []

        model.samples.each {sample ->
            HashMap mappedSampleProps = mapProperties(sample.properties)
            mappedSamples << new RepoWizSample(sample.sampleName,mappedSampleProps)
        }

        geoSubmissionModel = new SubmissionModel(mappedProject,mappedSamples)
    }

    HashMap mapProperties(HashMap<String,String> properties){
        HashMap<String,String> mappedProperties = new HashMap<>()
       properties.each {k,v ->
           //map the property key to geo term
           String mappedTerm = mapper.getGeoTerm(k)
           mappedProperties.put(mappedTerm, v)
        }

        return mappedProperties
    }

}
