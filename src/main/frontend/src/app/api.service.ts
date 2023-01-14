import {HttpClient} from '@angular/common/http';
import {Injectable} from "@angular/core";

import {Observable} from "rxjs";
import {Cipher} from "./model/cipher.model";
import {Decipher} from "./model/decipher.model";
import {Input} from "./model/input.model";

@Injectable()
export class ApiService {
  private readonly URL = "/api/v1/";

  constructor(private httpClient: HttpClient) {
  }

  getCipher(input: Input): Observable<Cipher> {
    return this.httpClient.post<Cipher>(this.URL + "cipher", input);
  }

  getDecipher(input: Input): Observable<Decipher> {
    return this.httpClient.post<Decipher>(this.URL + "decipher", input);
  }

}
