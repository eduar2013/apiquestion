package com.apiquestion.apiquestion.services;

import com.apiquestion.apiquestion.dao.QuestionRepository;
import com.apiquestion.apiquestion.dao.SequencesRepository;
import com.apiquestion.apiquestion.documents.Question;
import com.apiquestion.apiquestion.documents.Sequences;
import com.apiquestion.apiquestion.mappers.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionServicesImpl implements  QuestionService {

    @Autowired
    QuestionRepository questionRepository;



    @Override
    public Flux<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Mono<Question> findByNumber(int numberQuestion) {
        return questionRepository.findByNumber(numberQuestion);
    }

    @Override
    public void loadAll() {
        try {
            List<ListPath> files = this.getListFiles("../loadQuestions");

            if (files.size() == 0) {
                System.out.println("Empty directory, no files to upload");
            }


            files.forEach(f -> {
                Question q = QuestionMapper.fileToQuestion(f.getPath());
                this.saveQuestion(q);
                try {
                    this.moveFile(f.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });



            /*files.stream().map(f -> {

                return QuestionMapper.fileToQuestion(f.getPath());
                    }


            ).map(q-> saveQuestion(q));*/




            /*ExecutorService executor = Executors.newSingleThreadExecutor();
            List<Future<Integer>> result = executor.invokeAll(files);


            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);

            result.stream().map(x -> {
                try {
                    return x.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return 0;
            }).sorted().forEach(System.out::println);*/

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
;
    }



    /**
     *
     * @return list of files
     * @throws IOException
     */
    private List<ListPath> getListFiles(String directory) throws IOException {
        Path path = Path.of(directory);
        List<ListPath> files = new ArrayList();
        if (Files.exists(path) && Files.isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                directoryStream.forEach(f -> files.add(new ListPath(f)));
            }
        }
        return  files;
    }

    private int saveQuestion(Question question) {
        System.out.println(question);
        //getNumberQuestion(question);
        //question.setNumber(numberQuestion);

        questionRepository.insert(question).subscribe();
        return question.getNumber();
    }

    @Autowired
    SequencesRepository sequencesRepository;

    private  void getNumberQuestion(Question q) {

        Mono<Sequences> sequencesMono = sequencesRepository.findById("question_number");

         sequencesMono.map(s-> {
            q.setNumber(s.getSequence_value());
            return s;
        });



    }

    /**
     *
     */
    private void moveFile(Path pathFile) throws IOException {
        Path loadedDirectory = Path.of("../loaded");
        Files.move(pathFile, loadedDirectory.resolve(pathFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }
}
