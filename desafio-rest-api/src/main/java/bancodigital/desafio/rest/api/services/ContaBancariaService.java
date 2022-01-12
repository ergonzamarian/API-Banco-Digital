package bancodigital.desafio.rest.api.services;

import bancodigital.desafio.rest.api.model.ContaBancariaModel;
import bancodigital.desafio.rest.api.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository repository;

    public ContaBancariaModel cadastrarContaBancaria(ContaBancariaModel conta, String nomeCompleto, String cpf){
        if(repository.findByCpfContains(cpf).isEmpty()){
            conta.setNome(nomeCompleto);
            conta.setCpf(cpf);
            return repository.save(conta);
        }else{
            throw new RuntimeException("Pessoa já cadastrada com este CPF: " + repository.findByCpfContains(conta.cpf));
        }
    }

    public List<ContaBancariaModel> consultarTodasAsContasExistentes(){
        return repository.findAll();
    }

    public ResponseEntity consultarContaBancariaPorID(Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public void depositarNaContaPorId(Integer id,float valorDeposito){
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

    public void transferenciaEntreDuasContasPorId(Integer idOrigem, Integer idDestino, float valorTransferencia){

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
    public void deletarContaBancariaPorId(@PathVariable("id") Integer id){
        this.repository.deleteById(id);
    }


}
