
export function parseDataUpload(file: File) {
    const formData: FormData = new FormData();
    formData.append('files', file);
    return formData;
}

export function parseDataUploadList(files: Array<File>) {
    const formData: FormData = new FormData();
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < files.length; i++) {
        formData.append('file' + files[i].name, files[i]);
    }
    return formData;
}
