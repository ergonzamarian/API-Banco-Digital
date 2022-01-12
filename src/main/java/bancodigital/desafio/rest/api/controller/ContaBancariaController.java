package bancodigital.desafio.rest.api.controller;

import bancodigital.desafio.rest.api.model.ContaBancariaModel;
import bancodigital.desafio.rest.api.repository.ContaBancariaRepository;
import bancodigital.desafio.rest.api.services.ContaBancariaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Banco Digital")
@CrossOrigin(origins = "*")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaRepository repository;

    @Autowired
    private ContaBancariaService service;

    @PostMapping(path = "/conta/cadastrarConta")
    @ApiOperation(value = "Cadastrar uma nova Conta")
    public ContaBancariaModel cadastrarContaBancaria(
            @ApiIgnore ContaBancariaModel conta,
            @RequestParam(value = "nomeCompleto", required = false) String nomeCompleto,
            @RequestParam(value = "cpf", required = false) String cpf){

        return this.service.cadastrarContaBancaria(conta,nomeCompleto,cpf);
    }

    @GetMapping(path = "/conta/buscarTodas")
    @ApiOperation(value = "Consultar os dados de Todas as Contas")
    public List<ContaBancariaModel> consultarTodasAsContasExistentes(){
        return this.service.consultarTodasAsContasExistentes();
    }

    @GetMapping(path = "/conta/buscarPorId/{id}")
    @ApiOperation(value = "Consultar os Dados da Conta bancária através do Id")
    public ResponseEntity consultarContaBancariaPorID(@PathVariable("id") Integer id){
        return this.service.consultarContaBancariaPorID(id);
    }

    @PutMapping (path = "/conta/depositar/")
    @ApiOperation(value = "Depositar em uma conta Através do Id")
    public void depositarNaContaPorId(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "valorDeposito", required = false) float valorDeposito){
        this.service.depositarNaContaPorId(id, valorDeposito);
    }

    @PutMapping (path = "/conta/transferencia/")
    @ApiOperation(value = "Transferência entre duas contas através do Id de Origem e Id de Destino")
    public void transferenciaEntreDuasContasPorId(
            @RequestParam(value = "idOrigem", required = false) Integer idOrigem,
            @RequestParam(value = "idDestino", required = false) Integer idDestino,
            @RequestParam(value = "valorTransferencia", required = false) float valorTransferencia){
        this.service.transferenciaEntreDuasContasPorId(idOrigem, idDestino, valorTransferencia);
    }
    @DeleteMapping(path = "/conta/deletar/{id}")
    @ApiOperation(value = "Deletar uma Conta Bancária através do Id")
    public void deletarContaBancariaPorId(@PathVariable("id") Integer id){
        this.service.deletarContaBancariaPorId(id);
    }
}
