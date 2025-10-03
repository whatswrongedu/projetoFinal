package com.crud.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.app.models.Pessoa;
import com.crud.app.repository.AppRepository;

@Controller
public class CrudController {
    @Autowired
    private AppRepository csr;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/cadastrar", method=RequestMethod.GET)
    public String cadastrar() {
        return "cadastrar";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/nfr")
    public String nfr() {
        return "nfr";
    }

    @RequestMapping("/lfl")
    public String lfl() {
        return "lfl";
    }

    @RequestMapping("/bb")
    public String bb() {
        return "bb";
    }

    @RequestMapping("/coc")
    public String coc() {
        return "coc";
    }

    @RequestMapping("/hm")
    public String hm() {
        return "hm";
    }

    @RequestMapping("/ultraviolence")
    public String ultraviolence() {
        return "ultraviolence";
    }

    @RequestMapping("/btd")
    public String btd() {
        return "btd";
    }

    @RequestMapping("/paradise")
    public String paradise() {
        return "paradise";
    }

    @RequestMapping("/oceanblvd")
    public String oceanblvd() {
        return "oceanblvd";
    }


    @RequestMapping(value="/cadastrar", method=RequestMethod.POST)
    public String cadastrar(Pessoa usuario) {
        csr.save(usuario);
        return "redirect:/cadastrar";
    }

    // lista todos os usuários cadastrados
    @RequestMapping(value="/listarUsuarios", method=RequestMethod.GET)
    public ModelAndView listarUsuarios() {
        ModelAndView mv = new ModelAndView("listar");
        Iterable<Pessoa> usuarios = csr.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    // alterar
    @RequestMapping(value="/alterarUsuario/{idPessoa}", method=RequestMethod.GET)
    public ModelAndView alterarUsuario(@PathVariable("idPessoa") long idPessoa) {
        Pessoa usuario = csr.findByIdPessoa(idPessoa);
        ModelAndView mv = new ModelAndView("alterarUsuario");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping(value="/alterarUsuario/{idPessoa}", method=RequestMethod.POST)
    public String alterarUsuario(@Validated Pessoa usuario, BindingResult result, RedirectAttributes attributes) {
        csr.save(usuario);
        return "redirect:/listarUsuarios";
    }

    // excluir
@RequestMapping(value = "/excluirUsuario/{idPessoa}", method = RequestMethod.GET)
public String excluirUsuario(@PathVariable("idPessoa") long idPessoa) {
    Pessoa usuario = csr.findByIdPessoa(idPessoa);
    if (usuario != null) {
        csr.delete(usuario);
    }
    return "redirect:/listarUsuarios";
}
}
