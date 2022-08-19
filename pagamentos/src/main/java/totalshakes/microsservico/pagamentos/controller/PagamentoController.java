package totalshakes.microsservico.pagamentos.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshakes.microsservico.pagamentos.dto.PagamentoDto;
import totalshakes.microsservico.pagamentos.model.Pagamento;
import totalshakes.microsservico.pagamentos.service.PagamentoService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Pagamento>> obterTodosPagamentos(@PageableDefault(size = 10) Pageable paginacao){
        return new ResponseEntity<>(pagamentoService.obterTodos(paginacao), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> obterPagamento(@PathVariable @NotNull Long id) {
        Pagamento pagamento = pagamentoService.obterPorId(id);

        return ResponseEntity.ok(pagamento);
    }

    @PostMapping("/criar-pagamento")
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody @Valid PagamentoDto pagamentoDto) {
        Pagamento pagamento = new Pagamento();
        BeanUtils.copyProperties(pagamentoDto, pagamento);
        pagamentoService.criarPagamento(pagamento);
        return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto pagamentoDto) {
        Pagamento pagamentoAtualizado = new Pagamento();
        BeanUtils.copyProperties(pagamentoDto, pagamentoAtualizado);
        pagamentoService.atualizarPagamento(id, pagamentoAtualizado);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pagamento> deletarPagamento(@PathVariable @NotNull Long id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirmar")
    public void confirmarPagamento(@PathVariable @NotNull Long id){
        pagamentoService.confirmarPagamento(id);
    }
}
