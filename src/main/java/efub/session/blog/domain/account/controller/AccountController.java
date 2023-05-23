package efub.session.blog.domain.account.controller;

import efub.session.blog.domain.account.domain.Account;
<<<<<<< HEAD
import efub.session.blog.domain.account.dto.request.AccountUpdateRequestDto;
import efub.session.blog.domain.account.dto.request.SignUpRequestDto;
import efub.session.blog.domain.account.dto.response.AccountResponseDto;
=======
import efub.session.blog.domain.account.dto.AccountResponseDto;
import efub.session.blog.domain.account.dto.SignUpRequestDto;
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
import efub.session.blog.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
<<<<<<< HEAD
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto) {
=======
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
        Long id = accountService.signUp(requestDto);
        Account findAccount = accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

<<<<<<< HEAD
    @GetMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
=======
    @GetMapping
    @ResponseStatus(value=HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
        Account findAccount = accountService.findAccountById(accountId);
        return AccountResponseDto.from(findAccount);
    }

<<<<<<< HEAD
    @PatchMapping("/profile/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto update(@PathVariable final Long accountId, @RequestBody @Valid final AccountUpdateRequestDto requestDto) {
        Long id = accountService.update(accountId, requestDto);
        Account findAccount = accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    @PatchMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable long accountId) {
        accountService.withdraw(accountId);
        return "성공적으로 탈퇴가 완료되었습니다.";
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable long accountId) {
        accountService.delete(accountId);
        return "성공적으로 탈퇴가 완료되었습니다.";
=======
    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable long accountId){
        accountService.delete(accountId);
        return "성공적으로 탈퇴가 완료되었습니다";
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    }
}
