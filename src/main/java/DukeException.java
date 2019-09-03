public abstract class DukeException extends Exception{
    protected Ui ui;

    public DukeException(Ui ui){
        this.ui = ui;
    }
    public abstract void print();
}
