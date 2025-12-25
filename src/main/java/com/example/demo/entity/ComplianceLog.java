@Entity
public class ComplianceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "reading_id")
    private SensorReading reading;  // <-- add this

    // getters and setters
    public SensorReading getReading() {
        return reading;
    }

    public void setReading(SensorReading reading) {
        this.reading = reading;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
