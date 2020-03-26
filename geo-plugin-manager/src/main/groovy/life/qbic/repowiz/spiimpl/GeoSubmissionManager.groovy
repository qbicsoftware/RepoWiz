package life.qbic.repowiz.spiimpl

import life.qbic.repowiz.finalise.api.SubmissionManager
import life.qbic.repowiz.mapping.GeoMapper
import life.qbic.repowiz.prepare.model.SubmissionModel
import life.qbic.repowiz.prepare.model.RepoWizProject
import life.qbic.repowiz.prepare.model.RepoWizSample
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class GeoSubmissionManager implements SubmissionManager{

    private SubmissionModel geoSubmissionModel
    private GeoMapper mapper

    private static final Logger LOG = LogManager.getLogger(GeoSubmissionManager.class)

    GeoSubmissionManager(){
        LOG.info "Creating Geo Submission Manager"
        mapper = new GeoMapper()
    }

    @Override
    SubmissionModel validateSubmissionModel(SubmissionModel model) {//idea load the upload/submission type from the model

        //todo load all required fields for valid submission


        //todo load all accepted fields
        //todo answer with validation status and missing fields
        return model
    }

    @Override
    void downloadSubmission() {
        //todo download template for user
    }

    def mapMetadata(SubmissionModel model){
        HashMap projectProps = model.project.properties

        List<HashMap> sampleProps = []
        model.samples.each {repoWizSample ->
            //map properties of sample
        }

    }

    void defineSubmissionTemplate(String uploadType){
        templatePath = "templates/"

        switch (uploadType){
            case "hts": //defined in the repository description (json file)
                templatePath += "seq_template_v2.1.xlsx"
                templateSheets.add("METADATA TEMPLATE")
                break
            case "affymetrix_GE":
                //whole gene expression
                //todo ask for platform accession number, if not provided chose template with platform fill out
                templatePath = ""
                templateSheets.add("")
                break
        }
    }

}