package bancodigital.desafio.rest.api.controller;

import bancodigital.desafio.rest.api.model.ContaBancariaModel;
import bancodigital.desafio.rest.api.repository.ContaBancariaRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Banco Digital")
@CrossOrigin(origins = "*")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaRepository repository;

    @PostMapping(path = "/conta/cadastrarConta")
    @ApiOperation(value = "Cadastrar uma nova Conta")
    public ContaBancariaModel cadastrarContaBancaria(
            @ApiIgnore ContaBancariaModel conta,
            @RequestParam(value = "nomeCompleto", required = false) String nomeCompleto,
            @RequestParam(value = "cpf", required = false) String cpf){
        if(repository.findByCpfContains(cpf).isEmpty()){
            conta.setNome(nomeCompleto);
            conta.setCpf(cpf);
            return repository.save(conta);
        }else{
            throw new RuntimeException("Pessoa já cadastrada com este CPF: " + repository.findByCpfContains(conta.cpf));
        }
    }

    @GetMapping(path = "/conta/buscarTodas")
    @ApiOperation(value = "Consultar os dados de Todas as Contas")
    public List<ContaBancariaModel> consultarTodasAsContasExistentes(){
        return repository.findAll();
    }

    @GetMapping(path = "/conta/buscarPorId/{id}")
    @ApiOperation(value = "Consultar os Dados da Conta bancária através do Id")
    public ResponseEntity consultarContaBancariaPorID(@PathVariable("id") Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping (path = "/conta/depositar/")
    @ApiOperation(value = "Depositar em uma conta Através do Id")
    public void depositarNaContaPorId(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "valorDeposito", required = false) float valorDeposito){
        if(!this.repository.findById(id).isEmpty()) {

            if (valorDeposito > 0.0 && valorDeposito <= 2000.0) {
                float saldo = repository.findBySaldoPorId(id) + valorDeposito;
                this.repository.realizaDepositoPorId(id, saldo);
            } else if (valorDeposito <= 0.0) {
                throw new RuntimeException("Valor deve ser maior do que zero, o valor informado não é compatível: " + valorDeposito);
            } else {
                throw new RuntimeException("Por questão de segurança cada transação de depósito não pode ser maior do que R$2.000");
            }
        }else{
            throw new RuntimeException("Conta não localizada para Deposito");
        }
    }

    @PutMapping (path = "/conta/transferencia/")
    @ApiOperation(value = "Transferência entre duas contas através do Id de Origem e Id de Destino")
    public void transferenciaEntreDuasContasPorId(
            @RequestParam(value = "idOrigem", required = false) Integer idOrigem,
            @RequestParam(value = "idDestino", required = false) Integer idDestino,
            @RequestParam(value = "valorTransferencia", required = false) float valorTransferencia){

        if(!this.repository.findById(idOrigem).isEmpty() && !this.repository.findById(idDestino).isEmpty()){
            if(valorTransferencia>0.0 && valorTransferencia <= 2000.0){
                float saldoAtualizadoDaOrigem = repository.findBySaldoPorId(idOrigem) - valorTransferencia;
                if(saldoAtualizadoDaOrigem < 0.0){
                    throw new RuntimeException("Você não possui fundos suficientes para essa transação. O valor máximo para transação é de R$" + repository.findBySaldoPorId(idOrigem));
                }else{
                    float saldoAtualizadoDaDestino = repository.findBySaldoPorId(idDestino) + valorTransferencia;
                    this.repository.realizaDepositoPorId(idOrigem, saldoAtualizadoDaOrigem);
                    this.repository.realizaDepositoPorId(idDestino, saldoAtualizadoDaDestino);
                }
            }else if(valorTransferencia <= 0.0){
                throw new RuntimeException("Valor deve ser maior do que zero, o valor informado não é compatível");
            }else{
                throw new RuntimeException("Por questão de segurança cada transação de depósito não pode ser maior do que R$2.000");
            }
        }else{
            throw new RuntimeException("Conta não localizada para transferência");

        }
    }
    @DeleteMapping(path = "/conta/deletar/{id}")
    @ApiOperation(value = "Deletar uma Conta Bancária através do Id")
    public void deletarContaBancariaPorId(@PathVariable("id") Integer id){
        this.repository.deleteById(id);
    }
}
