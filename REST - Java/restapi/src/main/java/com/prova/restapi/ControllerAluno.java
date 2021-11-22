package com.prova.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prova.restapi.database.RepositorioAluno;
import com.prova.restapi.entidade.Aluno;

@RestController
public class ControllerAluno {
    @Autowired
    private RepositorioAluno repositorio;

    @RequestMapping(value = "/aluno", method = RequestMethod.GET)
    @ResponseBody
    public List<Aluno> Get() {
        return repositorio.findAll();
    }

    @RequestMapping(value = "/aluno/{ra}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Aluno> GetById(@PathVariable("ra") Long ra)
    {
        Optional<Aluno> aluno = repositorio.findById(ra);
        if(aluno.isPresent())
            return new ResponseEntity<Aluno>(aluno.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/aluno", method = RequestMethod.POST)
    @ResponseBody
    public Aluno Post(@RequestBody Aluno aluno)
    {
        return repositorio.save(aluno);
    }

    @RequestMapping(value = "/aluno/{ra}", method =  RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Aluno> Put(@PathVariable("ra") Long ra, @RequestBody Aluno newAluno)
    {
        Optional<Aluno> oldAluno = repositorio.findById(ra);
        if(oldAluno.isPresent()){
            Aluno aluno = oldAluno.get();
            aluno.setNome(newAluno.getNome());
            aluno.setCurso(newAluno.getCurso());
            aluno.setCidade(newAluno.getCidade());
            aluno.setSemestre(newAluno.getSemestre());
            aluno.setCpf(newAluno.getCpf());
            repositorio.save(aluno);
            return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/aluno/{ra}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> Delete(@PathVariable("ra") Long ra)
    {
        Optional<Aluno> aluno = repositorio.findById(ra);
        if(aluno.isPresent()){
            repositorio.delete(aluno.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}