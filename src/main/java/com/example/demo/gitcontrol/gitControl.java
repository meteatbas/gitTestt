package com.example.demo.gitcontrol;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class gitControl  {

    public void initLocal() throws IOException, URISyntaxException {
        File localpath = new File("C:/Users/meteatbas/Desktop/Döküman/projecttwo/");
        try {

            Git git = Git.init().setDirectory(localpath).call();
            git.add().addFilepattern(".").call();
            git.commit().setMessage("Init").setAll(true).call();
            git.branchCreate().setForce(true).setName("Development").call();
            git.branchCreate().setForce(true).setName("Controladores").call();
            git.branchCreate().setForce(true).setName("Servicios").call();
            git.branchCreate().setForce(true).setName("Repositorios").call();
            git.branchCreate().setForce(true).setName("Entidades").call();
            git.commit().setMessage("Branches creadas").call();
            } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public void initRemote() throws GitAPIException, IOException {
        FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();

        try {
//            Git.cloneRepository()
//                    .setURI("https://github.com/meteatbas/oyla")
//                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider("","" ))
//                    .setDirectory(new File("C:/Users/meteatbas/Desktop/Döküman/projectthree/"))
//                    .call();

//            Repository repository = repositoryBuilder.setGitDir(new File("C:\\Users\\meteatbas\\Desktop\\Döküman\\projectthree"))
//                    .readEnvironment() // scan environment GIT_* variables
//                    .findGitDir() // scan up the file system tree
//                    .setMustExist(true)
//                    .build();
//
//            repository.create();
            // Create a few new files

            File localpath = new File("C:/Users/meteatbas/Desktop/Döküman/projecttwo/");
            Git git = Git.init().setDirectory(localpath).call();

//            git.commit().setMessage("Initial commit Mete").call();
//            System.out.println("Committed file " + localpath + " to repository at " + git.getRepository().getDirectory());

            System.out.println("Created repository: " + git.getRepository().getDirectory());
            File myFile = new File(git.getRepository().getDirectory().getParent(), "testfile");

            git.add().addFilepattern(".").call();

            CommitCommand commitCommand = git.commit().setMessage("init project")
                    .setCommitter("commitModel.getUsername()", "ddd");
            commitCommand.setMessage("initial commit").call();

            if (!myFile.createNewFile()) {
                throw new IOException("Could not create file " + myFile);
            }

            List<Ref> call = git.branchList().call();

            for(Ref ref : call ){
                int i = 1;
                System.out.println("rama" + i + ref);
                i++;
                git = Git.init().setDirectory(localpath).call();
                git.remoteAdd().setUri(new URIish("https://github.com/meteatbas/parcial1")).setName("origin").call();
                git.push().setRemote("https://github.com/meteatbas/parcial1").setCredentialsProvider(new UsernamePasswordCredentialsProvider("","" )).setPushAll().add(".").call();
            }

        } catch (GitAPIException | URISyntaxException e) {
            e.printStackTrace();
        }

    }


}
