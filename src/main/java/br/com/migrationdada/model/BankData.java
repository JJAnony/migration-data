package br.com.migrationdada.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
public class BankData {

    private Long id;

    private Long person_id;

    private int agency;

    private int account;

    private int bank;
}
