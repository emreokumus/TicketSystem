package com.ticketsystem.accountservice.service;

import com.ticketsystem.accountservice.dto.AccountDto;
import com.ticketsystem.accountservice.entity.Account;
import com.ticketsystem.accountservice.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Optional;



@Service //Belli iş mantığını barındıran metotları barındıran service classımıza vermemiz gereken anotasyon.
//@Component 'de kullanılabilir ama o biraz daha utilty amacıyla kullanabileceğimiz classlara vermemiz gereken anotasyondur.
public class AccountServiceImpl implements AccountService {
    //Bu katmanda Request ve Response(ResponseEntity vb.) ile ilgili bir şey bilmez bu yüzden olmaz, bu katman iş katmanıdır.
    //Bu katman kendi nesne tiplerimizi biliriz ve onlarla ilgili işleri yaparız.
    //Kısaca iş mantığını barındıran metotlar burada olur.

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccountDto getAccount(String id) {
        Account account=accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Kayıt bulunamadi"));
        //Repository'den gelen Account nesnesini AccountDto mapleyip öyle döndürecek. Eğer Id'li kayıt yoksa Exception fırlatacak.
        return modelMapper.map(account,AccountDto.class);
    }

    @Transactional
    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        //Burada API'dan(arayüzden) gelen AccountDto nesnesini Account nesnesine mapliyorum.
        Account account=modelMapper.map(accountDto,Account.class);
        account=accountRepository.save(account);
        //bu (performans açısından daha mantıklı)
        accountDto.setId(account.getId());
        return accountDto;
        //veya bu
        //return modelMapper.map(account,AccountDto.class);
    }

    @Transactional
    @Override
    public AccountDto updateAccount(String id,AccountDto accountDto) {
        Assert.isNull(accountDto.getId(),"Id cannot be null");
        Optional<Account> account=accountRepository.findById(id);
        Account updatedAccount=account.map((item->{
            //null kontrolleri koyabiliriz
            item.setBirthDate(accountDto.getBirthDate());
            item.setName(accountDto.getName());
            item.setSurname(accountDto.getSurname());
            return item;
        })).orElseThrow(()->new RuntimeException("Kayıt bulunamadı."));
        return modelMapper.map(accountRepository.save(updatedAccount),AccountDto.class);
    }

    @Transactional
    @Override
    public void deleteAccount(String id) {
        Account account=accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Kayıt bulunamadi"));
        accountRepository.delete(account);
    }
    //pagination içindeki kayıtları dto kayıtlarına çevirererek slice veya page olarak dönmemiz gerekecek.
    @Override
    public Slice<AccountDto> getAccounts(Pageable pageable){
        Slice<Account> accounts = accountRepository.findAll(pageable);
        return accounts.map(account -> modelMapper.map(account,AccountDto.class));
    }

}