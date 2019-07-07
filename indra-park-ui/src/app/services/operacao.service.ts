import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Operacao} from '../models/operacao';
import {catchError, tap} from 'rxjs/internal/operators';

const API_URL = 'http://localhost:8080/api/operacao/';
const httpOptions = { headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class OperacaoService {

  constructor(private http: HttpClient) {}

  getAll(): Observable<Operacao[]> {
    return this.http.get<Operacao[]>(API_URL)
      .pipe(
        tap(produtos => console.log('leu as operações')),
        catchError(this.handleError('getAll()', []))
      );
  }

  entrada(operacao): Observable<Operacao> {
    return this.http.post<Operacao>(API_URL, operacao, httpOptions).pipe(
      tap((o: Operacao) => console.log(`fez a entrada com a placa=${o.placa} `),
        catchError(this.handleError<Operacao>('entrada()')))
    );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
