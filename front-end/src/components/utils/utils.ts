export const capitaliseEachWord = (str: string) => {
  return str
    .split("")
    .map(
      (word) =>
        word.charAt(0).toLocaleUpperCase() +
        word.substring(1).toLocaleLowerCase()
    )
    .join("");
};
