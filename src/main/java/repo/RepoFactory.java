package repo;

public class RepoFactory {
    public  static IRepo getRepo(){ return new DBRepo();}

}
