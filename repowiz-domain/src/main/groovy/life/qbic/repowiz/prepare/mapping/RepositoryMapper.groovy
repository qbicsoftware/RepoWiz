package life.qbic.repowiz.prepare.mapping

interface RepositoryMapper {

    String mapPropertiesToRepoWiz(String property)
    String mapPropertiesToOutput(String property) //toRepository / toRepo ?
    //def mapDuplicates(String prop,String level)

}