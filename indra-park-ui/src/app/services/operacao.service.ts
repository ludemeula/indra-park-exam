import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Veiculo} from '../models/veiculo';
import {catchError, tap} from 'rxjs/internal/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class OperacaoService {

  private readonly API_URL = 'http://localhost:8080/api/operacao/';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Veiculo[]> {
    return this.http.get<Veiculo[]>(this.API_URL)
      .pipe(
        tap(produtos => console.log('leu os produtos')),
        catchError(this.handleError('getProdutos', []))
      );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
