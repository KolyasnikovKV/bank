package ru.kolyasnikovkv.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpringBootApplicationBankTest {
    @Test
    public void contextLoads(){

    }
}
