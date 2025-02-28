package com.app.ellp.Module.Colaborador.Service;

import com.app.ellp.Module.Colaborador.DTOs.CreateColaboradorDTO;
import com.app.ellp.Module.Colaborador.Domain.Colaborador;
import com.app.ellp.Module.Colaborador.Repository.ColaboradorRepository;
import com.app.ellp.Module.User.DTOs.CreateUserDTO;
import com.app.ellp.Module.User.Domain.User;
import com.app.ellp.Module.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private UserService userService;

    public Colaborador findByEmail(String email) {
        Optional<Colaborador> colaborador = colaboradorRepository.findByEmail(email);
        if(colaborador.isPresent()) return colaborador.get();
        throw new UsernameNotFoundException("Usuário não encontrado!");
    }

    public Colaborador findById(String id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        if(colaborador.isPresent()) return colaborador.get();
        throw new UsernameNotFoundException("Usuário não encontrado!");
    }

    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }

    public List<Colaborador> findByNome(String nome) {
        return colaboradorRepository.findByNome(nome);
    }

    public Colaborador register(CreateColaboradorDTO json) {
        if(colaboradorRepository.findByEmail(json.email()).isPresent() || colaboradorRepository.findByRegistro(json.registro()).isPresent())
            throw new IllegalArgumentException("Erro: E-mail/Registro já cadastrado - " + json.email() + " " + json.registro());

        User user = userService.register(new CreateUserDTO(json.login(), json.password(), json.role()));
        Colaborador colaborador = new Colaborador(json.nome(), json.email(), json.registro(), user);

        return colaboradorRepository.save(colaborador);
    }

    public Colaborador updateById(String id, CreateColaboradorDTO json) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        if(!colaborador.isPresent())
            throw new UsernameNotFoundException("Usuário não encontrado!");
        Optional<User> user = Optional.ofNullable(userService.updateUserByLogin(
                colaborador.get().getUser().getLogin(),
                new CreateUserDTO(json.login(), json.password(), json.role())
        ));
        if(user.isPresent()) {
            colaborador.get().setNome(json.nome());
            colaborador.get().setEmail(json.email());
            colaborador.get().setRegistro(json.registro());
            colaborador.get().setUser(user.get());
            return colaboradorRepository.save(colaborador.get());
        }
        throw new RuntimeException("Erro ao atualizar os dados!");
    }

    public void deleteById(String id) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        if(!colaborador.isPresent())
            throw new UsernameNotFoundException("Usuário não encontrado!");
        userService.deleteUserByLogin(colaborador.get().getUser().getLogin());
        colaboradorRepository.delete(colaborador.get());
    }
}