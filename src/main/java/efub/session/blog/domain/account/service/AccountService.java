package efub.session.blog.domain.account.service;

import efub.session.blog.domain.account.domain.Account;
<<<<<<< HEAD
import efub.session.blog.domain.account.dto.request.AccountUpdateRequestDto;
import efub.session.blog.domain.account.dto.request.SignUpRequestDto;
import efub.session.blog.domain.account.repository.AccountRepository;
=======
import efub.session.blog.domain.account.dto.SignUpRequestDto;
import efub.session.blog.domain.account.repositoy.AccountRepository;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

<<<<<<< HEAD
    public Long signUp(SignUpRequestDto requestDto) {
        if (existsByEmail(requestDto.getEmail())) {
=======
    public Long signUp(SignUpRequestDto requestDto){
        if(existsByEmail(requestDto.getEmail())){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.getEmail());
        }
        Account account = accountRepository.save(requestDto.toEntity());
        return account.getAccountId();
    }

<<<<<<< HEAD
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
=======
    @Transactional(readOnly=true)
    public boolean existsByEmail(String email){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
        return accountRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("해당 ID를 가진 Account를 찾을 수 없습니다. ID=" + id)));
    }

    public Long update(Long accountId, AccountUpdateRequestDto requestDto) {
        Account account = findAccountById(accountId);
        account.updateAccount(requestDto.getBio(), requestDto.getNickname());
        return account.getAccountId();
    }

    @Transactional
    public void withdraw(Long accountId) {
        Account account = findAccountById(accountId);
        account.withdrawAccount();
    }

    public void delete(Long accountId) {
=======
    public Account findAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Account를 찾을 수 없습니다. id="+id));
    }

    public void delete(Long accountId){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
        Account account = findAccountById(accountId);
        accountRepository.delete(account);
    }
}
