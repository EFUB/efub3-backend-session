package efub.session.blog.account.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.SignUpRequestDto;
import efub.session.blog.account.repostitory.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Long signUp(SignUpRequestDto requestDto) throws IllegalAccessException {
        if(existsByEmail(requestDto.getEmail())){
            throw new IllegalAccessException("이미 존재하는 email입니다."+requestDto.getEmail());
        }
        Account account=accountRepository.save(requestDto.toEntity());
        return account.getAccountId();
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return accountRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Account findAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 Account를 찾을 수 없습니다. id="+id));
    }
    public void delete(Long accountId){
        Account account=findAccountById(accountId);
        accountRepository.delete(account);
    }
}
