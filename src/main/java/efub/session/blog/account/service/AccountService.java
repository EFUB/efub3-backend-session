package efub.session.blog.account.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.AccountUpdateRequestDto;
import efub.session.blog.account.dto.SignUpRequestDto;
import efub.session.blog.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Long signUp(SignUpRequestDto requestDto){

        if (existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.getEmail());
        }
        Account account = accountRepository.save(requestDto.toEntity());
        return account.getAccountId();

    }

    public Long update(Long accountId, AccountUpdateRequestDto requestDto) {
        Account account = findAccountById(accountId);
        account.updateAccount(requestDto.getBio(), requestDto.getNickname());
        return account.getAccountId();
    }

    @Transactional
    public void withdraw(Long accountId) {
        Account account = findAccountById(accountId);   // 해당 id를 가진 Account 객체를 찾아와서
        account.withdrawAccount();  // 그 객체의 상태를 변경
    }

    public void delete(Long accountId) {
        Account account = findAccountById(accountId);   // 해당 id를 가진 Account 객체를 찾아와서
        accountRepository.delete(account);  // 그 객체를 저장소에서 삭제
    }


    /*
    readonly 메소드들
     */
    // 해당 이메일을 가진 계정이 존재하는지 여부를 리턴
    @Transactional(readOnly = true) // 읽기만 하는 동작이므로, readOnly = true를 붙여 메모리사용을 줄인다
    public boolean existsByEmail(String email){
        return accountRepository.existsByEmail(email);
    }

    // 아이디를 기준으로 계정 하나 찾기
    @Transactional(readOnly = true)
    public Account findAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Account를 찾을 수 없습니다. id = "+id));
    }

    // 이메일을 기준으로 계정 하나 찾기
    @Transactional(readOnly = true)
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("해당 email을 가진 Account를 찾을 수 없습니다. email = " + email));
    }

}
