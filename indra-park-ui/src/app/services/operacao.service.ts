import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Operacao} from '../models/operacao';
import {catchError, tap} from 'rxjs/internal/operators';
import {Dashboard} from '../models/dashboard';

const API_URL = 'http://localhost:8080/api/operacao';
const httpOptions = { headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class OperacaoService {

  constructor(private http: HttpClient) {}

  getById(id: number): Observable<Operacao> {
    const url = `${API_URL}/${id}`;
    return this.http.get<Operacao>(url).pipe(
      tap(_ => console.log(`leu a operação id=${id}`)),
      catchError(this.handleError<Operacao>(`getById id=${id}`))
    );
  }

  getAll(): Observable<Operacao[]> {
    return this.http.get<Operacao[]>(API_URL)
      .pipe(
        tap(operacoes => console.log('leu as operações')),
        catchError(this.handleError('getAll()', []))
      );
  }

  pesquisa(dataEntrada: string, dataSaida: string, placa: string): Observable<Operacao[]> {
    const url = `${API_URL}/pesquisar`;
    let params = new HttpParams();
    if (dataEntrada)
      params = params.append('dataEntrada', dataEntrada);
    if (dataSaida)
      params = params.append('dataSaida', dataSaida);
    if (placa)
      params = params.append('placa', placa);

    return this.http.get<Operacao[]>(url, {params})
      .pipe(
        tap(_ => console.log('fez a pesquisa')),
        catchError(this.handleError('pesquisa()', []))
      );
  }

  entrada(operacao): Observable<Operacao> {
    return this.http.post<Operacao>(API_URL, operacao, httpOptions).pipe(
      tap((o: Operacao) => console.log(`fez a entrada com a placa=${o.placa} `),
        catchError(this.handleError<Operacao>('entrada()')))
    );
  }

  saida(id): Observable<Operacao> {
    const url = `${API_URL}/${id}`;
    return this.http.put(url, httpOptions).pipe(
      tap((o: Operacao) => console.log(`fez a saida com o id=${id} `),
        catchError(this.handleError<Operacao>('saida()')))
    );
  }

  calculo(modelo: string, dataHoraEntrada: string): Observable<number> {
    let params = new HttpParams();
    params = params.append('modelo', modelo).append('dataHoraEntrada', dataHoraEntrada);
    const url = `${API_URL}/calcular`;

    return this.http.get<number>(url, {params})
      .pipe(
      tap(_ => console.log(`calculou o valor do modelo=${modelo} `),
        catchError(this.handleError('calculo')))
    );
  }

  dashboard() {
    const url = `${API_URL}/dashboard`;
    return this.http.get<Dashboard[]>(url).pipe(
      tap(_ => console.log('carregou a dashboard')),
      catchError(this.handleError('dashboard()', []))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
