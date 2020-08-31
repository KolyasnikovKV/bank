package ru.kolyasnikovkv.bank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "operation")
public class Operation extends AbstractEntity {
    @JsonFormat(pattern ="yyyy-MM--dd")
    private Date ddate = new Date();
    private Account srcAccount;
    private Account dstAccount;
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal amount =  new BigDecimal("0.00");
    private String comment = "";

    @Column
    @Temporal(TemporalType.DATE)
    public Date getDdate(){
        return ddate;
    }
    public void setDdate(Date ddate){
        this.ddate = ddate;
    }

    @ManyToOne
    @JoinColumn(name = "src_account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Account getSrcAccount(){
        return srcAccount;
    }
    public void setSrcAccount(Account srcAccount){
        this.srcAccount = srcAccount;
    }

    @ManyToOne
    @JoinColumn(name = "dst_account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Account getDstAccount(){
        return dstAccount;
    }
    public void setDstAccount(Account dstAccount){
        this.dstAccount = dstAccount;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    @Override
    public String toString(){
        return "Operation{" +
                "id=" + id +
                ", srcAccount=" + srcAccount.getId() +
                ", dstAccount=" + dstAccount.getId() +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(!(obj instanceof Account)) return false;
        Account account = (Account) obj;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, srcAccount, dstAccount, amount);
    }
}
