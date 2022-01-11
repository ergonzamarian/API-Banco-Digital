package bancodigital.desafio.rest.api.repository;

import bancodigital.desafio.rest.api.model.ContaBancariaModel;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ContaBancariaRepository extends CrudRepository<ContaBancariaModel,Integer> {

    List<ContaBancariaModel> findAll();
    @Query(
            value = "SELECT cpf FROM contaBancaria AS C WHERE C.cpf = ?1"
    )
    List<String> findByCpfContains(String cpf);

    @Query(
            value = "SELECT C.saldo FROM contaBancaria AS C WHERE C.id = ?1"
    )
    float findBySaldoPorId(Integer id);

    @Modifying
    @Query(
            value = "UPDATE contaBancaria C SET C.saldo = ?2 WHERE C.id= ?1"
    )
    int realizaDepositoPorId(Integer id, float saldo);

}
