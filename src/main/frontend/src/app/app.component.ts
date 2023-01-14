import {Component} from '@angular/core';
import {ApiService} from "./api.service";
import {Input} from "./model/input.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  pattern: string = "";
  results: string[];

  constructor(protected apiService: ApiService) {
    this.results = [];
  }

  getCipher() {
    var input: Input = new Input();
    input.pattern = this.pattern;
    this.apiService.getCipher(input).subscribe((cipher) => {
      this.results = new Array(cipher.result.value)
    });
  }

  getDecipher() {
    var input: Input = new Input();
    input.pattern = this.pattern;
    this.apiService.getDecipher(input).subscribe((cipher) => {
      this.results = cipher.result.map((pattern) => {
        return pattern.value
      });
    });
  }
}
