import { NgbDateParserFormatter,NgbDate, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Injectable } from '@angular/core';

function padNumber(value: number | null) {
  if (!isNaN(value) && value !== null) {
    return `0${value}`.slice(-2);
  }
  return '';
}

@Injectable()
export class NgbDateCustomParserFormatter extends NgbDateParserFormatter {
  parse(value: string): NgbDateStruct | null {
    if (value) {
      const dateParts = value.trim().split('/');

      let dateObj: NgbDateStruct = { day: <any>null, month: <any>null, year: <any>null }
      const dateLabels = Object.keys(dateObj);

      dateParts.forEach((datePart, idx) => {
        dateObj[dateLabels[idx]] = parseInt(datePart, 10) || <any>null;
      });
      return dateObj;
    }
    return null;
  }

  public formatDate(date: NgbDateStruct | NgbDate | null): string {
    return date ?
        `${padNumber(date.day)}/${padNumber(date.month)}/${date.year || ''}` :
        '';
  }

  format(date: NgbDateStruct | null): string {
    return
    // NgbDateCustomParserFormatter.formatDate(date);
  }
}
