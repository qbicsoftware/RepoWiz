package life.qbic.repowiz.prepare

import life.qbic.repowiz.prepare.projectSearch.ProjectSearchService


class ProjectSearchConnector implements ProjectSearchService{

    @Override
    HashMap<String, String> getProjectMetadata(String projectID) {
        return null
    }

    @Override
    HashMap<String, String> getExperimentMetadata(String experimentID) {
        return null
    }

    @Override
    HashMap<String, String> getSampleMetadata(String projectID) {
        return null
    }

    @Override
    def getFilesForSamples(String sampleID) {
        return null
    }
}
