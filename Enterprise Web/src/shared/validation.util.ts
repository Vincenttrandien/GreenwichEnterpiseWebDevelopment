
export class validationUtil {

    static CAPACITY = 'MB';

    static EMAIL = '@'

    /*Kích thước file cho phép upload*/
    static MAX_SIZE_FILE_UPLOAD = 50; // MB

    /*Kích thước file cho phép upload kho tài liệu*/
    static MAX_SIZE_FILE_UPLOAD_ADMIN = 100; // MB

    /*Kích thước file cho phép upload*/
    static MAX_SIZE_IMAGE_UPLOAD = 10; // MB

    /*độ dài tối da input code*/
    static CODE_MAXLENGTH = 50;

    /*độ dài tối da input text*/
    static TEXT_MAXLENGTH = 256;

    /*file chấp nhận trên fontend*/
    static ACCEPT_EXCEL = '.xlsx, .xls';

    /*file chấp nhận trên fontend*/
    static ACCEPT_IMAGE = 'image/*';

    /*file chấp nhận trên fontend*/
    static ACCEPT_WORD = '.doc, .docx';

    static ACCEPT_FILE = '.doc, .docx, .xlsx, .xls, .pdf, image/*';

    static ACCEPT_ADMIN_DOC = '.zip,.rar,.7zip,.xlsx,.xls,.doc,.docx,.ppt,.pptx,.pdf,image/*,.xml,text/plain';

    /*fortmat so nguyen(, => 10,000,000)*/
    static PATTERN_NUMBER = '1.0';

    /*fortmat so le(, => 10,000,000.10)*/
    static PATTERN_NUMBER_DECIMAL = '1.2-2';

    /*fortmat tien(10,000,000 đ)*/
    static CURRENCY = 'VND';
    static LOCALE = 'vi';

    static checkExcelFile(file: File) {
        if ('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' === file.type || 'application/vnd.ms-excel' === file.type) {
            return true;
        }
        return false;
    }

    static checkFileType (file: File) {
      const dots = file.name.split('.');
      const fileType = '.' + dots[dots.length - 1];
      const type = ['.xlsx, .xls, .doc, .docx, .pdf, image/* ,.txt'];
      if (type.indexOf(fileType) > -1) {
          return true;
      }
      return false;
    }

    static checkUploadFileAdminTool(file: File) {
        const dots = file.name.split('.');
        const fileType = '.' + dots[dots.length - 1];
        const type = ['.zip', '.rar', '.7zip', '.xlsx', '.xls', '.doc', '.docx', '.ppt', '.pptx', '.pdf', '.jpg', '.png', '.xml', '.txt'];
        if (type.indexOf(fileType) > -1) {
            return true;
        }
        return false;
    }

    static checkSizeFileUploadAdminTool(file: File) {
        if ((file.size / 1024 / 1024) < this.MAX_SIZE_FILE_UPLOAD_ADMIN) {
            return true;
        }
        return false;
    }

    static checkSizeFileUpload(file: File) {
        if ((file.size / 1024 / 1024) < this.MAX_SIZE_FILE_UPLOAD) {
            return true;
        }
        return false;
    }

    static ACCEPT_OFFICE = ".xlsx,.xls,.doc,.docx,.pdf,image/*";

    static checkUploadFileOffice(file: File) {
        var dots = file.name.split(".")
        var fileType = "." + dots[dots.length-1];
        var type = [".xlsx",".xls",".doc",".docx",".ppt",".pptx",".pdf",".jpg",".png"];
        if(type.indexOf(fileType) > -1) {
            return true;
        }
        return false;
     }

}

