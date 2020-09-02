package ru.kolyasnikovkv.bank.critery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationCritery {
    public List<Long> ids = new ArrayList<>();
    public Date toDate;
    public Date fromDate;
    public Long clientId;
    public Long srcAccountId;
    public Long dstAccountId;
}
