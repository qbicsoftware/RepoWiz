package life.qbic.repowiz

import life.qbic.repowiz.cli.SubmissionPresenter
import life.qbic.repowiz.find.MatchingRepositoriesOutput
import life.qbic.repowiz.prepare.PrepareSubmissionInput
import life.qbic.repowiz.prepare.PrepareSubmissionOutput
import life.qbic.repowiz.select.SelectRepositoryInput
import life.qbic.repowiz.select.SelectRepositoryOutput
import life.qbic.repowiz.submit.FinaliseSubmission

//todo do i really want to keep one class implementing so many interfaces?
class SubmissionHandler implements MatchingRepositoriesOutput, SelectRepositoryOutput, PrepareSubmissionOutput{

    SubmissionPresenter presenter

    SelectRepositoryInput repositoryInput
    PrepareSubmissionInput prepareSubmissionInput
    FinaliseSubmission finaliseSubmissionInput

    SubmissionHandler(SubmissionPresenter presenter){
        this.presenter = presenter
    }

    SubmissionHandler(SelectRepositoryInput repositoryInput, SubmissionPresenter presenter){
        this.repositoryInput = repositoryInput
        this.presenter = presenter
    }

    SubmissionHandler(PrepareSubmissionInput prepareSubmissionInput, SubmissionPresenter presenter){
        this.prepareSubmissionInput = prepareSubmissionInput
        this.presenter = presenter
    }

    SubmissionHandler(FinaliseSubmission finaliseSubmissionInput, SubmissionPresenter presenter){
        this.finaliseSubmissionInput = finaliseSubmissionInput
        this.presenter = presenter
    }

    //MatchingRepositories output
    @Override
    def transferAnswerPossibilities(List<String> choices) {
        presenter.requestDecision(choices)
    }

    @Override
    def transferDecisionStack(List<String> decisions) {
        presenter.displayUserChoices(decisions)
    }

    @Override
    def transferRepositoryList(List<Repository> repositories) {
        //let the user decide which repo he wants
        repositoryInput.selectRepoFromSuggestions(repositories)
    }

    //selectRepository output
    @Override
    def chooseRepository(List<String> repositories) {
        presenter.requestRepository(repositories)
    }

    @Override
    def selectedRepository(Repository repository) {
        presenter.displayUserChoices([repository.name])
        prepareSubmissionInput.prepareSubmissionToRepository(repository)
    }

    //prepare submission use case
    @Override
    def transferQuestion(List<String> uploadTypes) { //todo rename: transfer uploadtypes from usecase to handler
        presenter.requestUploadType(uploadTypes)
    }

    @Override
    def displayAnswer(String choice) { //todo rename: selected uploadtype is shown
        presenter.displayUserChoices([choice])
    }

    @Override
    def transferProjectFiles(List<String> files) {
        return null
    }

    @Override
    def transferProjectMetadata(List<File> filledTemplates) {
        return null
    }


}
