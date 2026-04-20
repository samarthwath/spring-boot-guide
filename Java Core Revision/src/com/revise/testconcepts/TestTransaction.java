import com.revise.testconcepts.Transaction;

void main() {
    Transaction transactionFirst = new Transaction("samarth", "IT");
    Transaction transactionSecond = new Transaction("samarth", "IT");
    System.out.println(transactionFirst.hashCode());
    System.out.println(transactionSecond.hashCode());
    System.out.println(transactionFirst == transactionSecond);
    System.out.println(transactionFirst.equals(transactionSecond));
    System.out.println(transactionFirst.hashCode() == transactionSecond.hashCode());

    Set<Transaction> transactionSet = new HashSet<>();
    transactionSet.add(transactionFirst);
    transactionSet.add(transactionSecond);
    System.out.println(transactionSet.size());

    Map<Transaction, String> transactionMap = new HashMap();
    transactionMap.put(transactionFirst, "samarth");
    transactionMap.put(transactionSecond, "IT");
    System.out.println(transactionMap.size());

    System.out.println(transactionMap.keySet());
}
